package me.brkn.tallyapi.data.repository;

import me.brkn.tallyapi.data.model.CounterDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepository extends JpaRepository<CounterDao, Long> {

}
