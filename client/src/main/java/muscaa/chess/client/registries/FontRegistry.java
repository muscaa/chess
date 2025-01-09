package muscaa.chess.client.registries;

import muscaa.chess.Chess;
import muscaa.chess.client.assets.FontAsset;
import muscaa.chess.client.events.IRegisterFontsEventListener;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;

public class FontRegistry {
	
	public static final Registry<FontAsset> REG = Registries.create(Chess.NAMESPACE.path("fonts"), FontAsset::dispose);
	
	public static final FontAsset NULL = REG.register(new FontAsset(Chess.NAMESPACE.path("null"), null, 0));
	public static final FontAsset VARELA_24 = REG.register(new FontAsset(Chess.NAMESPACE.path("varela_24"), "fonts/VarelaRound-Regular.ttf", 24));
	public static final FontAsset VARELA_18 = REG.register(VARELA_24.derive(Chess.NAMESPACE.path("varela_18"), 18));
	public static final FontAsset MONTEZ_128 = REG.register(new FontAsset(Chess.NAMESPACE.path("montez_128"), "fonts/Montez-Regular.ttf", 128));
	
	public static void init() {
		Chess.EVENTS.call(
				IRegisterFontsEventListener.class,
				IRegisterFontsEventListener::onRegisterFontsEvent,
				new IRegisterFontsEventListener.RegisterFontsEvent(
						REG
						)
				);
		
		REG.lock();
	}
	
	public static void dispose() {
		REG.dispose();
	}
}
