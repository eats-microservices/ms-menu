package com.eats.msmenu.meals;

import com.eats.msmenu.exceptions.DatabaseException;
import com.eats.msmenu.exceptions.ResourceNotFoundException;
import com.eats.msmenu.meals.dto.CreateMealRequest;
import com.eats.msmenu.meals.dto.MealResponse;
import com.eats.msmenu.meals.dto.UpdateMealRequest;
import com.eats.msmenu.restaurants.RestaurantRepository;
import com.eats.msmenu.restaurants.dto.RestaurantResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/meals")
public class MealController {

    private final MealRepository repository;
    private final RestaurantRepository restaurantRepository;

    public MealController(MealRepository repository, RestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public ResponseEntity<Page<MealResponse>> listAllPaged(
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC, page = 0, size = 8) Pageable pageable) {
        Page<Meal> page = repository.findAll(pageable);

        Page<MealResponse> pageDto = page
                .map(p -> new MealResponse(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice(),
                        p.getAvailable(),
                        new RestaurantResponse(
                                p.getRestaurant().getName(),
                                p.getRestaurant().getCreatedAt(),
                                p.getRestaurant().getAddress(),
                                p.getRestaurant().getRate()
                        )
                ));
        return ResponseEntity.ok(pageDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealResponse> getMealById(@PathVariable Long id) {
        Optional<Meal> meal = repository.findById(id);

        if (meal.isEmpty()) {
            throw new ResourceNotFoundException("Meal with id " + id + " not found.");
        }

        return ResponseEntity
                .ok(new MealResponse(
                        meal.get().getId(),
                        meal.get().getName(),
                        meal.get().getDescription(),
                        meal.get().getPrice(),
                        meal.get().getAvailable(),
                        new RestaurantResponse(
                                meal.get().getRestaurant().getName(),
                                meal.get().getRestaurant().getCreatedAt(),
                                meal.get().getRestaurant().getAddress(),
                                meal.get().getRestaurant().getRate()
                        )
                ));
    }

    @PostMapping
    public ResponseEntity<Meal> insertIngredient(@RequestBody @Valid CreateMealRequest request) {
        Meal meal = request.toModel(restaurantRepository);
        meal = repository.save(meal);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/meals/{id}")
                .buildAndExpand(meal.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(meal);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateMeal(@PathVariable Long id, @RequestBody @Valid UpdateMealRequest request) {
        Optional<Meal> possibleMeal = repository.findById(id);

        if (possibleMeal.isEmpty()) {
            throw new ResourceNotFoundException("Meal with id " + id + " not found.");
        }

        Meal meal = possibleMeal.get();

        Meal updatedMeal = new Meal(
                meal.getId(),
                request.name(),
                request.description(),
                request.price(),
                meal.getAvailable(),
                meal.getRestaurant()
        );

        updatedMeal = repository.save(updatedMeal);
        return ResponseEntity.ok(updatedMeal);
    }

    // TODO PATCH AVAILABLE
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAvailability(@PathVariable Long id) {
        return null;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Id " + id + " not found.");
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseException("Data integrity violation!");
        }
        return ResponseEntity.noContent().build();
    }
}
