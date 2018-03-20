package domain.offer;

import domain.DTOMapper;
import dto.OfferDTO;
import exceptions.InvalidDateException;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;
import persistence.PersistenceServices;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class OfferManager {

    private final DTOMapper<Offer, OfferDTO> offerMapper;
    private final Set<Offer> offers;

    OfferManager() {
        this.offerMapper = new OfferMapper();
        offers = PersistenceServices.getInstance().findAllOffers().stream().map(offerMapper::toDomainObject).collect(Collectors.toSet());
    }

    public void addNewOffer(OfferDTO dto) throws ObjectAlreadyExistsException, InvalidDateException {
        Offer existingOffer = getOffer(dto.getId());
        if (existingOffer != null) {
            throw new ObjectAlreadyExistsException("offer", "OfferManager offers");
        }

        if (dto.getStartDate().getTime() > dto.getEndDate().getTime()) {
            throw new InvalidDateException("Offer start date is bigger than end date");
        }
        if (dto.getEndDate().getTime() < new Date().getTime()) {
            throw new InvalidDateException("End date is in the past");
        }

        Offer newOffer = offerMapper.toDomainObject(dto);
        offers.add(newOffer);
        PersistenceServices.getInstance().insertOffer(dto);
    }

    public void deleteOffer(int offerId) throws ObjectNotFoundException {
        Offer offer = getOffer(offerId);
        if (offer == null) {
            throw new ObjectNotFoundException("offer", "when deleting in OfferManager");
        }

        offers.remove(offer);
        PersistenceServices.getInstance().deleteOffer(offerId + "");
    }

    public Offer getProductOffer(int productId) {
        for (Offer offer : offers) {
            if (offer.getProductId() == productId) {
                return offer;
            }
        }
        return null;
    }

    private Offer getOffer(int offerId) {
        for (Offer offer : offers) {
            if (offer.getOfferId() == offerId) {
                return offer;
            }
        }
        return null;
    }

    public Set<Offer> getAllOffers() {
        return offers;
    }

}
