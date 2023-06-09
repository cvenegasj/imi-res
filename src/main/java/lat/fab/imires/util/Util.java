package lat.fab.imires.util;

import java.util.AbstractMap;
import java.util.Map;

public class Util {

    public static final Map<Integer, String> DIMENSION_TO_NAME_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<Integer, String>(1, "Product"),
            new AbstractMap.SimpleEntry<Integer, String>(2, "Resources"),
            new AbstractMap.SimpleEntry<Integer, String>(3, "Information"),
            new AbstractMap.SimpleEntry<Integer, String>(4, "Organization"),
            new AbstractMap.SimpleEntry<Integer, String>(5, "Innovation")
    );

    public static final Map<Integer, String> NUMBER_TO_NAME_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<Integer, String>(1, "Product"),
            new AbstractMap.SimpleEntry<Integer, String>(2, "Design"),
            new AbstractMap.SimpleEntry<Integer, String>(3, "Fabrication"),
            new AbstractMap.SimpleEntry<Integer, String>(4, "Materials"),
            new AbstractMap.SimpleEntry<Integer, String>(5, "Energy"),
            new AbstractMap.SimpleEntry<Integer, String>(6, "Logistics"),
            new AbstractMap.SimpleEntry<Integer, String>(7, "Sensing"),
            new AbstractMap.SimpleEntry<Integer, String>(8, "Processing"),
            new AbstractMap.SimpleEntry<Integer, String>(9, "Actuator"),
            new AbstractMap.SimpleEntry<Integer, String>(10, "Organization"),
            new AbstractMap.SimpleEntry<Integer, String>(11, "Impact"),
            new AbstractMap.SimpleEntry<Integer, String>(12, "Compliance"),
            new AbstractMap.SimpleEntry<Integer, String>(13, "Innovation"),
            new AbstractMap.SimpleEntry<Integer, String>(14, "Intellectual property"),
            new AbstractMap.SimpleEntry<Integer, String>(15, "Training")
    );

    public static final Map<String, String> COUNTRY_TO_CODE_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Afghanistan", "AFG"),
            new AbstractMap.SimpleEntry<>("Albania", "ALB"),
            new AbstractMap.SimpleEntry<>("Algeria", "DZA"),
            new AbstractMap.SimpleEntry<>("Andorra", "AND"),
            new AbstractMap.SimpleEntry<>("Angola", "AGO"),
            new AbstractMap.SimpleEntry<>("Antigua & Deps", "ATG"),
            new AbstractMap.SimpleEntry<>("Argentina", "ARG"),
            new AbstractMap.SimpleEntry<>("Armenia", "ARM"),
            new AbstractMap.SimpleEntry<>("Australia", "AUS"),
            new AbstractMap.SimpleEntry<>("Austria", "AUT"),
            new AbstractMap.SimpleEntry<>("Azerbaijan", "AZE"),
            new AbstractMap.SimpleEntry<>("Bahamas", "BHS"),
            new AbstractMap.SimpleEntry<>("Bahrain", "BHR"),
            new AbstractMap.SimpleEntry<>("Bangladesh", "BGD"),
            new AbstractMap.SimpleEntry<>("Barbados", "BRB"),
            new AbstractMap.SimpleEntry<>("Belarus", "BLR"),
            new AbstractMap.SimpleEntry<>("Belgium", "BEL"),
            new AbstractMap.SimpleEntry<>("Belize", "BLZ"),
            new AbstractMap.SimpleEntry<>("Benin", "BEN"),
            new AbstractMap.SimpleEntry<>("Bhutan", "BTN"),
            new AbstractMap.SimpleEntry<>("Bolivia", "BOL"),
            new AbstractMap.SimpleEntry<>("Bosnia Herzegovina", "BIH"),
            new AbstractMap.SimpleEntry<>("Botswana", "BWA"),
            new AbstractMap.SimpleEntry<>("Brazil", "BRA"),
            new AbstractMap.SimpleEntry<>("Brunei", "BRN"),
            new AbstractMap.SimpleEntry<>("Bulgaria", "BGR"),
            new AbstractMap.SimpleEntry<>("Burkina", "BFA"),
            new AbstractMap.SimpleEntry<>("Burundi", "BDI"),
            new AbstractMap.SimpleEntry<>("Cambodia", "KHM"),
            new AbstractMap.SimpleEntry<>("Cameroon", "CMR"),
            new AbstractMap.SimpleEntry<>("Canada", "CAN"),
            new AbstractMap.SimpleEntry<>("Cape Verde", "CPV"),
            new AbstractMap.SimpleEntry<>("Central African Rep", "CAF"),
            new AbstractMap.SimpleEntry<>("Chad", "TCD"),
            new AbstractMap.SimpleEntry<>("Chile", "CHL"),
            new AbstractMap.SimpleEntry<>("China", "CHN"),
            new AbstractMap.SimpleEntry<>("Colombia", "COL"),
            new AbstractMap.SimpleEntry<>("Comoros", "COM"),
            new AbstractMap.SimpleEntry<>("Congo", "COG"),
            new AbstractMap.SimpleEntry<>("Congo {Democratic Rep}", "COD"),
            new AbstractMap.SimpleEntry<>("Costa Rica", "CRI"),
            new AbstractMap.SimpleEntry<>("Croatia", "HRV"),
            new AbstractMap.SimpleEntry<>("Cuba", "CUB"),
            new AbstractMap.SimpleEntry<>("Cyprus", "CYP"),
            new AbstractMap.SimpleEntry<>("Czech Republic", "CZE"),
            new AbstractMap.SimpleEntry<>("Denmark", "DNK"),
            new AbstractMap.SimpleEntry<>("Djibouti", "DJI"),
            new AbstractMap.SimpleEntry<>("Dominica", "DMA"),
            new AbstractMap.SimpleEntry<>("Dominican Republic", "DOM"),
            new AbstractMap.SimpleEntry<>("East Timor", "TMP"),
            new AbstractMap.SimpleEntry<>("Ecuador", "ECU"),
            new AbstractMap.SimpleEntry<>("Egypt", "EGY"),
            new AbstractMap.SimpleEntry<>("El Salvador", "SLV"),
            new AbstractMap.SimpleEntry<>("Equatorial Guinea", "GNQ"),
            new AbstractMap.SimpleEntry<>("Eritrea", "ERI"),
            new AbstractMap.SimpleEntry<>("Estonia", "EST"),
            new AbstractMap.SimpleEntry<>("Ethiopia", "ETH"),
            new AbstractMap.SimpleEntry<>("Fiji", "FJI"),
            new AbstractMap.SimpleEntry<>("Finland", "FIN"),
            new AbstractMap.SimpleEntry<>("France", "FRA"),
            new AbstractMap.SimpleEntry<>("Gabon", "GAB"),
            new AbstractMap.SimpleEntry<>("Gambia", "GMB"),
            new AbstractMap.SimpleEntry<>("Georgia", "GEO"),
            new AbstractMap.SimpleEntry<>("Germany", "DEU"),
            new AbstractMap.SimpleEntry<>("Ghana", "GHA"),
            new AbstractMap.SimpleEntry<>("Greece", "GRC"),
            new AbstractMap.SimpleEntry<>("Grenada", "GRD"),
            new AbstractMap.SimpleEntry<>("Guatemala", "GTM"),
            new AbstractMap.SimpleEntry<>("Guinea", "GIN"),
            new AbstractMap.SimpleEntry<>("Guinea-Bissau", "GNB"),
            new AbstractMap.SimpleEntry<>("Guyana", "GUY"),
            new AbstractMap.SimpleEntry<>("Haiti", "HTI"),
            new AbstractMap.SimpleEntry<>("Honduras", "HND"),
            new AbstractMap.SimpleEntry<>("Hungary", "HUN"),
            new AbstractMap.SimpleEntry<>("Iceland", "ISL"),
            new AbstractMap.SimpleEntry<>("India", "IND"),
            new AbstractMap.SimpleEntry<>("Indonesia", "IDN"),
            new AbstractMap.SimpleEntry<>("Iran", "IRN"),
            new AbstractMap.SimpleEntry<>("Iraq", "IRQ"),
            new AbstractMap.SimpleEntry<>("Ireland {Republic}", "IRL"),
            new AbstractMap.SimpleEntry<>("Israel", "ISR"),
            new AbstractMap.SimpleEntry<>("Italy", "ITA"),
            new AbstractMap.SimpleEntry<>("Ivory Coast", "CIV"),
            new AbstractMap.SimpleEntry<>("Jamaica", "JAM"),
            new AbstractMap.SimpleEntry<>("Japan", "JPN"),
            new AbstractMap.SimpleEntry<>("Jordan", "JOR"),
            new AbstractMap.SimpleEntry<>("Kazakhstan", "KAZ"),
            new AbstractMap.SimpleEntry<>("Kenya", "KEN"),
            new AbstractMap.SimpleEntry<>("Kiribati", "KIR"),
            new AbstractMap.SimpleEntry<>("Korea North", "PRK"),
            new AbstractMap.SimpleEntry<>("Korea South", "KOR"),
            new AbstractMap.SimpleEntry<>("Kosovo", "XXK"),
            new AbstractMap.SimpleEntry<>("Kuwait", "KWT"),
            new AbstractMap.SimpleEntry<>("Kyrgyzstan", "KGZ"),
            new AbstractMap.SimpleEntry<>("Laos", "LAO"),
            new AbstractMap.SimpleEntry<>("Latvia", "LVA"),
            new AbstractMap.SimpleEntry<>("Lebanon", "LBN"),
            new AbstractMap.SimpleEntry<>("Lesotho", "LSO"),
            new AbstractMap.SimpleEntry<>("Liberia", "LBR"),
            new AbstractMap.SimpleEntry<>("Libya", "LBY"),
            new AbstractMap.SimpleEntry<>("Liechtenstein", "LIE"),
            new AbstractMap.SimpleEntry<>("Lithuania", "LTU"),
            new AbstractMap.SimpleEntry<>("Luxembourg", "LUX"),
            new AbstractMap.SimpleEntry<>("Macedonia", "MKD"),
            new AbstractMap.SimpleEntry<>("Madagascar", "MDG"),
            new AbstractMap.SimpleEntry<>("Malawi", "MWI"),
            new AbstractMap.SimpleEntry<>("Malaysia", "MYS"),
            new AbstractMap.SimpleEntry<>("Maldives", "MDV"),
            new AbstractMap.SimpleEntry<>("Mali", "MLI"),
            new AbstractMap.SimpleEntry<>("Malta", "MLT"),
            new AbstractMap.SimpleEntry<>("Marshall Islands", "MHL"),
            new AbstractMap.SimpleEntry<>("Mauritania", "MRT"),
            new AbstractMap.SimpleEntry<>("Mauritius", "MUS"),
            new AbstractMap.SimpleEntry<>("Mexico", "MEX"),
            new AbstractMap.SimpleEntry<>("Micronesia", "FSM"),
            new AbstractMap.SimpleEntry<>("Moldova", "MDA"),
            new AbstractMap.SimpleEntry<>("Monaco", "MCO"),
            new AbstractMap.SimpleEntry<>("Mongolia", "MNG"),
            new AbstractMap.SimpleEntry<>("Montenegro", "MNE"),
            new AbstractMap.SimpleEntry<>("Morocco", "MAR"),
            new AbstractMap.SimpleEntry<>("Mozambique", "MOZ"),
            new AbstractMap.SimpleEntry<>("Myanmar", "MMR"),
            //new AbstractMap.SimpleEntry<>("Burma", ""),
            new AbstractMap.SimpleEntry<>("Namibia", "NAM"),
            new AbstractMap.SimpleEntry<>("Nauru", "NRU"),
            new AbstractMap.SimpleEntry<>("Nepal", "NPL"),
            new AbstractMap.SimpleEntry<>("Netherlands", "NLD"),
            new AbstractMap.SimpleEntry<>("New Zealand", "NZL"),
            new AbstractMap.SimpleEntry<>("Nicaragua", "NIC"),
            new AbstractMap.SimpleEntry<>("Niger", "NER"),
            new AbstractMap.SimpleEntry<>("Nigeria", "NGA"),
            new AbstractMap.SimpleEntry<>("Norway", "NOR"),
            new AbstractMap.SimpleEntry<>("Oman", "OMN"),
            new AbstractMap.SimpleEntry<>("Pakistan", "PAK"),
            new AbstractMap.SimpleEntry<>("Palau", "PLW"),
            new AbstractMap.SimpleEntry<>("Panama", "PAN"),
            new AbstractMap.SimpleEntry<>("Papua New Guinea", "PNG"),
            new AbstractMap.SimpleEntry<>("Paraguay", "PRY"),
            new AbstractMap.SimpleEntry<>("Peru", "PER"),
            new AbstractMap.SimpleEntry<>("Philippines", "PHL"),
            new AbstractMap.SimpleEntry<>("Poland", "POL"),
            new AbstractMap.SimpleEntry<>("Portugal", "PRT"),
            new AbstractMap.SimpleEntry<>("Qatar", "QAT"),
            new AbstractMap.SimpleEntry<>("Romania", "ROM"),
            new AbstractMap.SimpleEntry<>("Russian Federation", "RUS"),
            new AbstractMap.SimpleEntry<>("Rwanda", "RWA"),
            new AbstractMap.SimpleEntry<>("St Kitts & Nevis", "KNA"),
            new AbstractMap.SimpleEntry<>("St Lucia", "LCA"),
            new AbstractMap.SimpleEntry<>("Saint Vincent & the Grenadines", "VCT"),
            new AbstractMap.SimpleEntry<>("Samoa", "WSM"),
            new AbstractMap.SimpleEntry<>("San Marino", "SMR"),
            new AbstractMap.SimpleEntry<>("Sao Tome & Principe", "STP"),
            new AbstractMap.SimpleEntry<>("Saudi Arabia", "SAU"),
            new AbstractMap.SimpleEntry<>("Senegal", "SEN"),
            new AbstractMap.SimpleEntry<>("Serbia", "SRB"),
            new AbstractMap.SimpleEntry<>("Seychelles", "SYC"),
            new AbstractMap.SimpleEntry<>("Sierra Leone", "SLE"),
            new AbstractMap.SimpleEntry<>("Singapore", "SGP"),
            new AbstractMap.SimpleEntry<>("Slovakia", "SVK"),
            new AbstractMap.SimpleEntry<>("Slovenia", "SVN"),
            new AbstractMap.SimpleEntry<>("Solomon Islands", "SLB"),
            new AbstractMap.SimpleEntry<>("Somalia", "SOM"),
            new AbstractMap.SimpleEntry<>("South Africa", "ZAF"),
            new AbstractMap.SimpleEntry<>("South Sudan", "SSD"),
            new AbstractMap.SimpleEntry<>("Spain", "ESP"),
            new AbstractMap.SimpleEntry<>("Sri Lanka", "LKA"),
            new AbstractMap.SimpleEntry<>("Sudan", "SDN"),
            new AbstractMap.SimpleEntry<>("Suriname", "SUR"),
            new AbstractMap.SimpleEntry<>("Swaziland", "SWZ"),
            new AbstractMap.SimpleEntry<>("Sweden", "SWE"),
            new AbstractMap.SimpleEntry<>("Switzerland", "CHE"),
            new AbstractMap.SimpleEntry<>("Syria", "SYR"),
            new AbstractMap.SimpleEntry<>("Taiwan", "TWN"),
            new AbstractMap.SimpleEntry<>("Tajikistan", "TJK"),
            new AbstractMap.SimpleEntry<>("Tanzania", "TZA"),
            new AbstractMap.SimpleEntry<>("Thailand", "THA"),
            new AbstractMap.SimpleEntry<>("Togo", "TGO"),
            new AbstractMap.SimpleEntry<>("Tonga", "TON"),
            new AbstractMap.SimpleEntry<>("Trinidad & Tobago", "TTO"),
            new AbstractMap.SimpleEntry<>("Tunisia", "TUN"),
            new AbstractMap.SimpleEntry<>("Turkey", "TUR"),
            new AbstractMap.SimpleEntry<>("Turkmenistan", "TKM"),
            new AbstractMap.SimpleEntry<>("Tuvalu", "TUV"),
            new AbstractMap.SimpleEntry<>("Uganda", "UGA"),
            new AbstractMap.SimpleEntry<>("Ukraine", "UKR"),
            new AbstractMap.SimpleEntry<>("United Arab Emirates", "ARE"),
            new AbstractMap.SimpleEntry<>("United Kingdom", "GBR"),
            new AbstractMap.SimpleEntry<>("United States", "USA"),
            new AbstractMap.SimpleEntry<>("Uruguay", "URY"),
            new AbstractMap.SimpleEntry<>("Uzbekistan", "UZB"),
            new AbstractMap.SimpleEntry<>("Vanuatu", "VUT"),
            new AbstractMap.SimpleEntry<>("Vatican City", "VAT"),
            new AbstractMap.SimpleEntry<>("Venezuela", "VEN"),
            new AbstractMap.SimpleEntry<>("Vietnam", "VNM"),
            new AbstractMap.SimpleEntry<>("Yemen", "YEM"),
            new AbstractMap.SimpleEntry<>("Zambia", "ZMB"),
            new AbstractMap.SimpleEntry<>("Zimbabwe", "ZWE")
    );
}
