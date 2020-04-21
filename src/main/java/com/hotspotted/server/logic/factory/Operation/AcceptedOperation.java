package com.hotspotted.server.logic.factory.Operation;

import com.hotspotted.server.controller.enums.Response;
import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.entity.HotSpotChange;
import com.hotspotted.server.entity.enums.RequestedChangeStatus;
import com.hotspotted.server.logic.factory.OperationHelper;
import com.hotspotted.server.service.HotSpotChangeService;
import com.hotspotted.server.service.HotSpotService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AcceptedOperation extends OperationHelper implements Operation {
    public AcceptedOperation(HotSpotChange hotSpotChange, HotSpotService hotSpotService, HotSpotChangeService hotSpotChangeService) {
        super(hotSpotChange, hotSpotService, hotSpotChangeService);
    }

    @Override
    public HotSpotChange execute() {

        HotSpot foundHotSpot = hotSpotService.findBySlug(hotSpotChange.getHotSpot().getSlug())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Response.NOT_FOUND.toString()));

        HotSpot hotSpotFromDTO = modelMapper.map(hotSpotChange, HotSpot.class);
        hotSpotFromDTO.setComments(foundHotSpot.getComments());
        hotSpotFromDTO.setCreator(foundHotSpot.getCreator());
        hotSpotFromDTO.setCreatedAt(foundHotSpot.getCreatedAt());
        hotSpotFromDTO.setSlug(foundHotSpot.getSlug());

        hotSpotFromDTO.setId(foundHotSpot.getId());

        hotSpotService.createOrUpdate(hotSpotFromDTO);
        hotSpotChange.setStatus(RequestedChangeStatus.ACCEPTED);
        return hotSpotChangeService.createOrUpdate(hotSpotChange);
    }
}
