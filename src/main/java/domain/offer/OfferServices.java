package domain.offer;

import dto.OfferDTO;
import exceptions.InvalidDateException;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OfferServices {

    private static OfferServices instance;
    private final OfferManager offerManager;

    private OfferServices() {
        offerManager = new OfferManager();
    }

    public void addNewOffer(OfferDTO dto) throws ObjectAlreadyExistsException, InvalidDateException {
        offerManager.addNewOffer(dto);
    }

    public void deleteOffer(int offerId) throws ObjectNotFoundException {
        offerManager.deleteOffer(offerId);
    }

    public Offer getProductOffer(int productId) {
        return offerManager.getProductOffer(productId);
    }

    public List<Offer> getActiveOffers() {
        return offerManager.getAllOffers().stream().filter(Offer::isActive).collect(Collectors.toList());
    }

    public Set<Offer> getAllOffers() {
        return offerManager.getAllOffers();
    }

    public static OfferServices getInstance() {
        if (instance == null) {
            instance = new OfferServices();
        }
        return instance;
    }

}
