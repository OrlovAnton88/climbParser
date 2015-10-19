package ru.anton.orlov.miracleguide.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anton.orlov.miracleguide.parser.model.Area;
import ru.anton.orlov.miracleguide.repository.AreaRepository;
import ru.anton.orlov.miracleguide.utils.FileUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by antonorlov on 20/10/15.
 */
@Service
//todo: propagetion transactional
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;


    @PostConstruct
    public void postConstruct() {

        String stalker = FileUtils.readFile("/Users/antonorlov/Documents/java/climbParser/src/main/resources/stalker.json");

        Area stakerArea = new Gson().fromJson(stalker, Area.class);

        areaRepository.save(stakerArea);

        String monrepo = FileUtils.readFile("/Users/antonorlov/Documents/java/climbParser/src/main/resources/monrepo.json");

        Area monrepoArea = new Gson().fromJson(monrepo, Area.class);

        areaRepository.save(monrepoArea);

    }


    @Transactional
    public List<Area> getAllAreas(){
        return areaRepository.findAll();
    }
}
