plugins {
    id("android-feature-base")
}

dependencies {
    api(project(Modules.Features.Base))
    api(project(Modules.Domain.Schedule))
}