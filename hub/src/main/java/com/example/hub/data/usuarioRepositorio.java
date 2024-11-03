package com.example.hub.data;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;


@Repository
public interface usuarioRepositorio extends JpaRepository<Usuario, Integer> {}