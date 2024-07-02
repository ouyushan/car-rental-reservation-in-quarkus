package com.github.ouyushan.reservation.inventory;

import io.smallrye.graphql.client.typesafe.api.GraphQLClientApi;
import org.eclipse.microprofile.graphql.Query;

import java.util.List;

/**
 * @description:
 * @author: ouyushan
 * @email: ouyushan@hotmail.com
 * @date: 2024/7/1 20:47
 */
@GraphQLClientApi(configKey = "inventory")
public interface GraphQLInventoryClient extends InventoryClient {

    @Query("cars")
    List<Car> allCars();
}
