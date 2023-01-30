package dev.iyare.service.drone.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Created by ochoge on 9/21/15.
 */
public class JsonUtil
{

	public static Gson getGson()
	{
		GsonBuilder builder = new GsonBuilder();
		builder.addDeserializationExclusionStrategy(new SuperclassExclusionStrategy());
		builder.addSerializationExclusionStrategy(new SuperclassExclusionStrategy());
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = builder.create();
		return gson;
	}

	public static <T> T fromJson(String json, Class<T> clazz)
	{

		return getGson().fromJson(json, clazz);
	}

	public static <T> String toJson(T object)
	{
		return getGson().toJson(object);
	}

	public static <T> List<T> fromJsonAsList(String json, Class<T> clazz)
	{
		Type type = new TypeToken<ArrayList<T>>()
		{
		}.getType();
		ArrayList<T> list = getGson().fromJson(json, type);
		return list;
	}

	public static <T> Set<T> fromJsonAsSet(String json, Class<T> clazz)
	{
		Type type = new TypeToken<HashSet<T>>()
		{
		}.getType();
		Set<T> set = getGson().fromJson(json, type);
		return set;
	}

	public static <T> List<T> fromJsonStringToList(String s, Class<T[]> clazz)
	{
		T[] arr = new Gson().fromJson(s, clazz);
		return Arrays.asList(arr);
	}

	public static <T> Map<String, T> fromJsonAsMap(String json, Class<T> clazz)
	{
		Type type = new TypeToken<HashMap<String, T>>()
		{
		}.getType();
		Map<String, T> map = getGson().fromJson(json, type);
		return map;
	}

	public static <T, S> T convert(S object, Class<T> tClass)
	{
		String json = toJson(object);
		T expected = fromJson(json, tClass);
		return expected;
	}
}

class SuperclassExclusionStrategy implements ExclusionStrategy
{
	public boolean shouldSkipClass(Class<?> arg0)
	{
		return false;
	}

	public boolean shouldSkipField(FieldAttributes fieldAttributes)
	{
		String fieldName = fieldAttributes.getName();
		Class<?> theClass = fieldAttributes.getDeclaringClass();

		return isFieldInSuperclass(theClass, fieldName);
	}

	private boolean isFieldInSuperclass(Class<?> subclass, String fieldName)
	{
		Class<?> superclass = subclass.getSuperclass();
		Field field;

		while (superclass != null)
		{
			field = getField(superclass, fieldName);

			if (field != null)
				return true;

			superclass = superclass.getSuperclass();
		}

		return false;
	}

	private Field getField(Class<?> theClass, String fieldName)
	{
		try
		{
			return theClass.getDeclaredField(fieldName);
		} catch (Exception e)
		{
			return null;
		}
	}
}