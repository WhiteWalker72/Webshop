package domain.offer;

import domain.DTOMapper;
import dto.OfferDTO;

public class OfferMapper implements DTOMapper<Offer, OfferDTO> {

    @Override
    public OfferDTO toDTO(Offer offer) {
        return offer.toOfferDTO();
    }

    @Override
    public Offer toDomainObject(OfferDTO dto) {
        return new Offer(dto.getId(), dto.getStartDate(), dto.getEndDate(), dto.getText(), dto.getOfferPrice(), dto.getProductId());
    }

}
