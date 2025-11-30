package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.ContenidoRepository;

@Service
public class ContenidoServiceImpl implements ContenidoService {
    @Autowired
    private ContenidoRepository repoContenido;
}
