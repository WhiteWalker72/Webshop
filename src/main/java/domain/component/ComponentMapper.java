package domain.component;

import domain.DTOMapper;
import dto.ComponentDTO;

abstract class ComponentMapper<T extends ShopComponent, U extends ComponentDTO> implements DTOMapper<T, U> {

    final IComponentManager<T, U> compManager;

    ComponentMapper(IComponentManager<T, U> compManager) {
        this.compManager = compManager;
    }

}
