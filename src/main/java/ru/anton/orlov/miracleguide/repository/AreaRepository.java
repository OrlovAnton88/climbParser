package ru.anton.orlov.miracleguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.orlov.miracleguide.parser.model.Area;

/**
 * Created by antonorlov on 20/10/15.
 */
public interface AreaRepository extends JpaRepository<Area, Long> {

}
