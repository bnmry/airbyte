/*
 * Copyright (c) 2024 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.cdk.command

import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton

@ConfigurationProperties("destination.config")
interface DestinationConfiguration : Configuration {
    /**
     * Micronaut factory which glues [ConnectorSpecificationSupplier] and
     * [DestinationConfigurationFactory] together to produce a [DestinationConfiguration] singleton.
     */
    @Factory
    private class MicronautFactory {
        @Singleton
        fun <I : ConnectorSpecification> destinationConfig(
            specificationSupplier: ConnectorSpecificationSupplier<I>,
            factory: DestinationConfigurationFactory<I, out DestinationConfiguration>,
        ): DestinationConfiguration = factory.make(specificationSupplier.get())
    }
}
