package com.soongjamm.bank;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class DependencyRuleTest {

    @Test
    void domainLayerDoesNotDependOnApplicationLayer() {
        noClasses()
                .that()
                .resideInAPackage("com.soongjamm.bank.account.domain..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage("com.soongjamm.bank.account.application")
                .check(new ClassFileImporter().importPackages("com.soongjamm.bank.account.."));
    }

    @Test
    void validateRegistrationContextArchitecture() {
    }
}
