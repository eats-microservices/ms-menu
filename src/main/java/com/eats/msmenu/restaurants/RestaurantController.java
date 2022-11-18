package com.eats.msmenu.restaurants;

import com.eats.msmenu.exceptions.DatabaseException;
import com.eats.msmenu.exceptions.ResourceNotFoundException;
import com.eats.msmenu.restaurants.dto.CreateRestaurantRequest;
import com.eats.msmenu.restaurants.dto.RestaurantResponse;
import com.eats.msmenu.restaurants.dto.UpdateRestaurantRequest;
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
import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantRepository repository;

    public RestaurantController(RestaurantRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Page<RestaurantResponse>> listAllPaged(
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC, page = 0, size = 8) Pageable pageable) {
        Page<Restaurant> page = repository.findAll(pageable);

        Page<RestaurantResponse> pageDto = page
                .map(p -> new RestaurantResponse(p.getName(), p.getCreatedAt(), p.getAddress(), p.getRate()));
        return ResponseEntity.ok().body(pageDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> getRestaurantById(@PathVariable Long id) {
        Optional<Restaurant> restaurant = repository.findById(id);

        if (restaurant.isEmpty()) {
            throw new ResourceNotFoundException("Resturant with id " + id + " not found.");
        }

        return ResponseEntity
                .ok(new RestaurantResponse(
                        restaurant.get().getName(),
                        restaurant.get().getCreatedAt(),
                        restaurant.get().getAddress(),
                        restaurant.get().getRate()
                ));
    }

    @PostMapping
    public ResponseEntity<RestaurantResponse> insertIngredient(@RequestBody @Valid CreateRestaurantRequest request) {
        Restaurant restaurant = request.toModel();
        restaurant = repository.save(restaurant);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/restaurants/{id}")
                .buildAndExpand(restaurant.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new RestaurantResponse(
                        restaurant.getName(),
                        restaurant.getCreatedAt(),
                        restaurant.getAddress(),
                        restaurant.getRate()
                ));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateRestaurant(@PathVariable Long id, @RequestBody @Valid UpdateRestaurantRequest request) {
        Optional<Restaurant> restaurant = repository.findById(id);

        if (restaurant.isEmpty()) {
            throw new ResourceNotFoundException("Restaurant with id " + id + " not found.");
        }

       Restaurant updatedRestaurant = new Restaurant(
               restaurant.get().getId(),
               request.name(),
               restaurant.get().getCreatedAt(),
               Instant.now(),
               request.address(),
               restaurant.get().getRate()
               );

        updatedRestaurant = repository.save(updatedRestaurant);
        return ResponseEntity.ok(updatedRestaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
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
