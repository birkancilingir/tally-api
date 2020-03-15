package me.brkn.tallyapi.service.counter.mapper;

import me.brkn.tallyapi.data.model.CounterDao;
import me.brkn.tallyapi.service.counter.model.Counter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CounterMapper {
    CounterMapper INSTANCE = Mappers.getMapper(CounterMapper.class);

    Counter mapToCounter(CounterDao dto);
}