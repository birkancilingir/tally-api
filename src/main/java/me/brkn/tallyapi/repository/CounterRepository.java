package me.brkn.tallyapi.repository;

import me.brkn.tallyapi.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepository extends JpaRepository<Counter, Long> {

}