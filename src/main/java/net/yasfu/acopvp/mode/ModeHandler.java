package net.yasfu.acopvp.mode;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Set;

public class ModeHandler {

    public static final HashMap<String, Class<?>> availableModes = new HashMap<>();

    /**
     * Uses reflection to check all playable Modes
     */
    public static void registerModes() {
        Reflections reflections = new Reflections();
        Set<Class<?>> modeClasses = reflections.getTypesAnnotatedWith(ModeData.class);

        Class<?>[] modeClassesMap = (Class<?>[]) modeClasses.toArray();

        for (Class<?> modeClass : modeClassesMap) {
            if (!modeClass.isAssignableFrom(Mode.class)) {
                continue;
            }

            ModeData modeData = modeClass.getAnnotation(ModeData.class);
            String modeName = modeData.name();

            availableModes.put(modeName, modeClass);
        }
    }

    /**
     * Create a mode instance from a mode name
     *
     * @param name Mode name
     * @return The instantiated mode
     */
    public static Mode createModeInstance(String name)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> modeClass = availableModes.get(name);

        if (modeClass == null || !modeClass.isAssignableFrom(Mode.class)) {
            return null;
        }

        Object modeObj = modeClass.getDeclaredConstructor().newInstance();
        return (Mode) modeObj;
    }

    public static boolean hasMode(String name) {
        return availableModes.containsKey(name);
    }

}
