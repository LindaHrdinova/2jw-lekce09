package cz.czechitas.java2webapps.lekce9.service;

import cz.czechitas.java2webapps.lekce9.entity.Osoba;
import cz.czechitas.java2webapps.lekce9.form.RokNarozeniForm;
import cz.czechitas.java2webapps.lekce9.repository.OsobaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Služba pro práci s osobami a adresami.
 */
@Service
public class OsobaService {
    private final OsobaRepository osobaRepository;

    @Autowired
    public OsobaService(OsobaRepository osobaRepository) {
        this.osobaRepository = osobaRepository;
    }

    /**
     * Vrací stránkovaný seznam všech osob v databázi seřazených podle příjmení a jména.
     */
    public Page<Osoba> seznamOsob(Pageable pageable) {
        return osobaRepository.findAll(pageable);
    }

    /**
     * Vrací stránkovaný seznam všech osob v databázi, které se narodili mezi uvedenými roky.
     */
    public Page<Osoba> seznamDleRokuNarozeni(RokNarozeniForm form, Pageable pageable) {
        return osobaRepository.findByRok(form.getOd(), form.getDo(), pageable);
    }

    public Page<Osoba> seznamDleZacatkuPrijmeni(String prijmeni, Pageable pageable) {
        return osobaRepository.findByPrijmeniStartingWithIgnoreCase(prijmeni, pageable);
    }

    public Page<Osoba> seznamDleNazvuObce(String obec, Pageable pageable) {
        return osobaRepository.findOsobaByAdresa_Obec(obec, pageable);
    }

    public Page<Osoba> seznamDleVeku(int vek, Pageable pageable) {
        LocalDate date = LocalDate.now().minusYears(vek);
        return osobaRepository.findByDatumNarozeniBefore(date, pageable);
    }

    public Page<Osoba> seznamDleZacatkuPrijmeniAJmena( String zacatekPrijmeni, String zacatekJmena, Pageable pageable) {
        return osobaRepository.findByPrijmeniAJmeno(zacatekPrijmeni, zacatekJmena, pageable);
    }

}
