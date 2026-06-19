package fr.swif.codecase_web.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * UserRepository
 * <i>de fr.swif.codecase_web.repository</i>
 * <hr>
 * <p>Repository qui fait le lien entre l'api et la webapp de User</p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 19/06/2026
 */

// @Slf4j permet de générer un champ de log
@Slf4j
// @Component est une annotation Spring qui marque une classe comme bean géré
// par le conteneur IoC (Inversion of Control) de Spring
// Spring scan tous les packages et instancie automatiquement toutes les classes
// annotées @Component
@Component
// @RequiredArgsConstructor génère automatiquement un constructeur prenant en
// paramètre tous les champs final et @NotNull de la classe
@RequiredArgsConstructor
public class UserRepository {



}
