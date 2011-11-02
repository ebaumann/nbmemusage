package de.elmar_baumann.nbmemusage;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.openide.util.NbBundle;

/**
 * @author Elmar Baumann
 */
public class SizeInfo {

    private static final long EX_BIBYTE = 1152921504606846976L;
    private static final long PE_BIBYTE = 1125899906842624L;
    private static final long TE_BIBYTE = 1099511627776L;
    private static final long GI_BIBYTE = 1073741824L;
    private static final long ME_BIBYTE = 1048576L;
    private static final long KI_BIBYTE = 1024L;
    private static final String NUMBER_FORMAT_SHORT_SIZE_FORMAT = "{0,number,#.#}";
    private static final String SHORT_SIZE_FORMAT_EX_BIBYTE = NUMBER_FORMAT_SHORT_SIZE_FORMAT + " EB";
    private static final String SHORT_SIZE_FORMAT_PE_BIBYTE = NUMBER_FORMAT_SHORT_SIZE_FORMAT + " PB";
    private static final String SHORT_SIZE_FORMAT_TE_BIBYTE = NUMBER_FORMAT_SHORT_SIZE_FORMAT + " TB";
    private static final String SHORT_SIZE_FORMAT_GI_BIBYTE = NUMBER_FORMAT_SHORT_SIZE_FORMAT + " GB";
    private static final String SHORT_SIZE_FORMAT_ME_BIBYTE = NUMBER_FORMAT_SHORT_SIZE_FORMAT + " MB";
    private static final String SHORT_SIZE_FORMAT_KI_BIBYTE = NUMBER_FORMAT_SHORT_SIZE_FORMAT + " KB";
    private static final Map<String, String> SIZE_INFO_OF_SHORT_SIZE_FORMAT = new HashMap<String, String>();

    static {
        SIZE_INFO_OF_SHORT_SIZE_FORMAT.put(SHORT_SIZE_FORMAT_EX_BIBYTE, NbBundle.getMessage(SizeInfo.class, "SizeInfo.Exbibyte"));
        SIZE_INFO_OF_SHORT_SIZE_FORMAT.put(SHORT_SIZE_FORMAT_PE_BIBYTE, NbBundle.getMessage(SizeInfo.class, "SizeInfo.Pebibyte"));
        SIZE_INFO_OF_SHORT_SIZE_FORMAT.put(SHORT_SIZE_FORMAT_TE_BIBYTE, NbBundle.getMessage(SizeInfo.class, "SizeInfo.Tebibyte"));
        SIZE_INFO_OF_SHORT_SIZE_FORMAT.put(SHORT_SIZE_FORMAT_GI_BIBYTE, NbBundle.getMessage(SizeInfo.class, "SizeInfo.Gibibyte"));
        SIZE_INFO_OF_SHORT_SIZE_FORMAT.put(SHORT_SIZE_FORMAT_ME_BIBYTE, NbBundle.getMessage(SizeInfo.class, "SizeInfo.Mebibyte"));
        SIZE_INFO_OF_SHORT_SIZE_FORMAT.put(SHORT_SIZE_FORMAT_KI_BIBYTE, NbBundle.getMessage(SizeInfo.class, "SizeInfo.Kibibyte"));
    }

    private String longInfo;
    private String shortInfo;

    public String getLongInfo() {
        return longInfo;
    }

    public String getShortInfo() {
        return shortInfo;
    }

    public static SizeInfo createSizeInfo(long bytes) {
        boolean isExBibytes = bytes >= EX_BIBYTE;
        boolean isPeBibytes = !isExBibytes && bytes >= PE_BIBYTE;
        boolean isTeBibytes = !isPeBibytes && bytes >= TE_BIBYTE;
        boolean isGiBibytes = !isTeBibytes && bytes >= GI_BIBYTE;
        boolean isMeBibytes = !isGiBibytes && bytes > ME_BIBYTE;
        String shortSizeFormat = isExBibytes
                ? SHORT_SIZE_FORMAT_EX_BIBYTE
                : isPeBibytes
                ? SHORT_SIZE_FORMAT_PE_BIBYTE
                : isTeBibytes
                ? SHORT_SIZE_FORMAT_TE_BIBYTE
                : isGiBibytes
                ? SHORT_SIZE_FORMAT_GI_BIBYTE
                : isMeBibytes
                ? SHORT_SIZE_FORMAT_ME_BIBYTE
                : SHORT_SIZE_FORMAT_KI_BIBYTE;
        String sizeInfoOfShortSizeFormat = SIZE_INFO_OF_SHORT_SIZE_FORMAT.get(shortSizeFormat);
        long divisorForSizeInMaximumUnits = isExBibytes
                ? EX_BIBYTE
                : isPeBibytes
                ? PE_BIBYTE
                : isTeBibytes
                ? TE_BIBYTE
                : isGiBibytes
                ? GI_BIBYTE
                : isMeBibytes
                ? ME_BIBYTE
                : KI_BIBYTE;
        String longSizeFormat = "{0,number}  Bytes ~ {1} {2}";

        double sizeInMaximumUnits = bytes / (double) divisorForSizeInMaximumUnits;
        SizeInfo sizeInfo = new SizeInfo();

        sizeInfo.shortInfo = MessageFormat.format(shortSizeFormat, sizeInMaximumUnits);
        sizeInfo.longInfo = MessageFormat.format(longSizeFormat, bytes, sizeInfo.shortInfo, sizeInfoOfShortSizeFormat);

        return sizeInfo;
    }

    private SizeInfo() {}
}
