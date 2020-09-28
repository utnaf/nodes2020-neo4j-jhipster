package com.nodes;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.nodes");

        noClasses()
            .that()
                .resideInAnyPackage("com.nodes.service..")
            .or()
                .resideInAnyPackage("com.nodes.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.nodes.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
