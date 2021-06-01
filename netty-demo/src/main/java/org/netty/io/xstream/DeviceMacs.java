package org.netty.io.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author rainy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("mappings")
public class DeviceMacs implements Serializable {

    private static final long serialVersionUID = 6221912228755109439L;
    @XStreamImplicit(itemFieldName = "mapping")
    private List<deviceMapping> mappings;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @XStreamAlias("mapping")
    public static class deviceMapping implements Serializable{

        private static final long serialVersionUID = 8306055793513106954L;
        @XStreamAlias("sn")
        private String sn;

        @XStreamAlias("mac")
        private String mac;
    }
}
