package com.project.uber.UberApp.CONFIGS;

import com.project.uber.UberApp.DTO.PointDTO;
import com.project.uber.UberApp.utils.GeometryUtils;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(PointDTO.class, Point.class).setConverter(context ->{
            PointDTO pointDTO = context.getSource();
            return GeometryUtils.createPoint(pointDTO);
        });

        mapper.typeMap(Point.class,PointDTO.class).setConverter(conntext ->{
            Point point = conntext.getSource();
            double coordinates[] = {
                    point.getX(),
                    point.getY()
            };
            return new PointDTO(coordinates);
        });
        return mapper;
    }
}
