package domain.component;

import dto.ComponentDTO;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;

public interface IComponentManager<T extends ShopComponent, U extends ComponentDTO> {

    void addNewComponent(U compDTO) throws ObjectAlreadyExistsException;

    void deleteComponent(T shopComponent) throws ObjectNotFoundException;

    Integer getId(ShopComponent shopComponent);

    T getComponent(int id);

}
