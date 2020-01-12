package com.jpn.lipo.excercise.lipoexcercise.repository;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class CurrentPriceRepository {

    private final StringRedisTemplate template;

    public CurrentPriceRepository(StringRedisTemplate template) {
        this.template = template;
    }

    public void add(String key, String value) {
        if(getBy(key) == null){
            template.opsForValue().set(key, value);
        }
        template.opsForValue().getAndSet(key, value);
    }

    public String getBy(String key) {
        return template.opsForValue().get(key);
    }

    public Set<String> getKeys(String patternKey) {
        return template.keys(patternKey);
    }

    public Set<String> getAllValuesBy(String patternKey) {
        final Set<String> keys = getKeys(patternKey);
        final Set<String> values = new HashSet<String>(keys.size());

        for (String key : keys) {
            values.add(getBy(key));
        }

        return values;
    }

    public void delete(String key) {
        template.opsForValue().getOperations().delete(key);
    }
}
