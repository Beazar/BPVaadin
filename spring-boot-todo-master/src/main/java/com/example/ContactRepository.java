package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
   // @Transactional
   // void deleteByDone(boolean delete);
}
