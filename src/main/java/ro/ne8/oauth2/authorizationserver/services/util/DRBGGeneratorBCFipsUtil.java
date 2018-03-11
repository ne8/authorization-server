package ro.ne8.oauth2.authorizationserver.services.util;

import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.EntropySourceProvider;
import org.bouncycastle.crypto.fips.FipsDRBG;
import org.bouncycastle.crypto.util.BasicEntropySourceProvider;
import org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider;
import org.bouncycastle.util.Strings;
import org.springframework.stereotype.Service;

import java.rmi.dgc.VMID;
import java.security.SecureRandom;
import java.security.Security;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;


@Service
public class DRBGGeneratorBCFipsUtil {


    private static final boolean PREDICTION_RESISTENCE_FOR_CRYPTOGRAPHIC_STRINGS = true;
    private static final int TIME_SKEW_ON_PURPOSE = 42000;

    //Each nonce shall be unique to the cryptographic module in which instantiation is performed, but need not be secret.

    private static final String TIMESTAMP_AS_NONCE = new SimpleDateFormat("yyyy.MM.DD.HH.mm.ss").format(new Timestamp(System.currentTimeMillis() + DRBGGeneratorBCFipsUtil.TIME_SKEW_ON_PURPOSE));


    private static final boolean PREDICTION_REISTENCE_FOR_NONCE = false;

    private static final byte[] personalizationString = Strings.toUTF8ByteArray(new VMID().toString());

    static {
        Security.addProvider(new BouncyCastleFipsProvider());
        DRBGGeneratorBCFipsUtil.setUpgradedDefaultDeterministicRandomNumberGenerator();
    }


    public static String generateSHA512RandomString() {
        final EntropySourceProvider sourceProvider = new BasicEntropySourceProvider(new SecureRandom(), true);

        final FipsDRBG.Builder drgb = FipsDRBG.SHA512.fromEntropySource(sourceProvider)
                .setSecurityStrength(256)
                .setEntropyBitsRequired(256)
                .setPersonalizationString(DRBGGeneratorBCFipsUtil.personalizationString);

        final SecureRandom generator = drgb.build(Strings.toByteArray(DRBGGeneratorBCFipsUtil.generateRandomNonce()), DRBGGeneratorBCFipsUtil.PREDICTION_RESISTENCE_FOR_CRYPTOGRAPHIC_STRINGS);

        final byte[] data = new byte[64];
        generator.nextBytes(data);
        return Base64.getEncoder().encodeToString(data);
    }


    private static String generateRandomNonce() {
        final EntropySourceProvider entropySource = new BasicEntropySourceProvider(new SecureRandom(), DRBGGeneratorBCFipsUtil.PREDICTION_REISTENCE_FOR_NONCE);

        final FipsDRBG.Builder drgb = FipsDRBG.SHA512.fromEntropySource(entropySource)
                .setSecurityStrength(256)
                .setEntropyBitsRequired(256)
                .setPersonalizationString(DRBGGeneratorBCFipsUtil.personalizationString);


        final SecureRandom random = drgb.build(Strings.toByteArray(DRBGGeneratorBCFipsUtil.TIMESTAMP_AS_NONCE), false);


        final byte[] data = new byte[64];
        random.nextBytes(data);
        return Base64.getEncoder().encodeToString(data);
    }

    private static void setUpgradedDefaultDeterministicRandomNumberGenerator() {
        final EntropySourceProvider entropySourcePovider = new BasicEntropySourceProvider(new SecureRandom(), true);

        final FipsDRBG.Builder drgb = FipsDRBG.SHA512.fromEntropySource(entropySourcePovider)
                .setSecurityStrength(256)
                .setEntropyBitsRequired(256);
        CryptoServicesRegistrar.setSecureRandom(drgb.build(Strings.toByteArray(DRBGGeneratorBCFipsUtil.generateRandomNonce()), false));

    }
}
