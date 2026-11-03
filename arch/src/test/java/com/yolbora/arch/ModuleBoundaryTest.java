package com.yolbora.arch;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

class ModuleBoundaryTest {

    private static final JavaClasses CLASSES =
            new ClassFileImporter().importPackages("com.yolbora");

    private static final ArchRule MODULES_DEPEND_DOWNWARD = layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .layer("api").definedBy("com.yolbora.api..")
            .layer("sim").definedBy("com.yolbora.sim..")
            .layer("ingest").definedBy("com.yolbora.ingest..")
            .layer("mission").definedBy("com.yolbora.mission..")
            .layer("planner").definedBy("com.yolbora.planner..")
            .layer("dispatch").definedBy("com.yolbora.dispatch..")
            .layer("spatial").definedBy("com.yolbora.spatial..")
            .layer("core").definedBy("com.yolbora.core..")
            .whereLayer("api").mayOnlyAccessLayers(
                    "sim", "ingest", "mission", "planner", "dispatch", "spatial", "core")
            .whereLayer("sim").mayOnlyAccessLayers(
                    "mission", "planner", "dispatch", "spatial", "core")
            .whereLayer("ingest").mayOnlyAccessLayers("spatial", "core")
            .whereLayer("mission").mayOnlyAccessLayers(
                    "planner", "dispatch", "spatial", "core")
            .whereLayer("planner").mayOnlyAccessLayers("dispatch", "spatial", "core")
            .whereLayer("dispatch").mayOnlyAccessLayers("spatial", "core")
            .whereLayer("spatial").mayOnlyAccessLayers("core")
            .whereLayer("core").mayNotAccessAnyLayer();

    @Test
    void modulesMayOnlyDependDownward() {
        MODULES_DEPEND_DOWNWARD.check(CLASSES);
    }

    @Test
    void modulesHaveNoCycles() {
        slices()
                .matching("com.yolbora.(*)..")
                .should()
                .beFreeOfCycles()
                .check(CLASSES);
    }
}