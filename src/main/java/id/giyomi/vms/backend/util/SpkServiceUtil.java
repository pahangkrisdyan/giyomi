package id.giyomi.vms.backend.util;

import id.giyomi.vms.backend.entity.Spk;
import id.giyomi.vms.backend.entity.SpkStatus;

import java.util.Date;
import java.util.Iterator;

public class SpkServiceUtil {
    public static Date getTanggalPesan(Spk spk) {
        if(spk.getSpkStatuses().size()<1){
            throw new Error("spkStatuses length equals to 0");
        }
        Iterator<SpkStatus> spkStatusIterator = spk.getSpkStatuses().iterator();
        while (spkStatusIterator.hasNext()){
            SpkStatus spkStatus = spkStatusIterator.next();
            //TODO: user dynamic status yang mengindikasikan spk tanggal pemesanan
            if(spkStatus.getStatus().getNama().equals("on_going")){
                return spkStatus.getCreatedAt();
            }
        }
        return null;
    }
}
