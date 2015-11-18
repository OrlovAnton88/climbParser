package ru.anton.orlov.miracleguide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anton.orlov.miracleguide.parser.model.Area;
import ru.anton.orlov.miracleguide.repository.AreaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by antonorlov on 20/10/15.
 */
@Service
//todo: propagetion transactional
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;


    @Transactional
    public List<Area> getAllAreas(){
        return areaRepository.findAll();
    }


    @Transactional
    public void saveArea(final Area area){
        areaRepository.save(area);
    }


    public Optional<Area> getArea(String name){
        return Optional.ofNullable(areaRepository.findOneByName(name));
    }

    public Optional<Area> getArea(Long Id){
        return Optional.ofNullable(areaRepository.findOneById(Id));
    }
}
