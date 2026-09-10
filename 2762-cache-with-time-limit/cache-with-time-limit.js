var TimeLimitedCache = function() {
    this.cache = new Map();
};

TimeLimitedCache.prototype.set = function(key, value, duration) {
    const existing = this.cache.get(key);
    if (existing) {
        clearTimeout(existing.timer);
    }
    
    const timer = setTimeout(() => {
        this.cache.delete(key);
    }, duration);

    this.cache.set(key, { value, timer });
    return Boolean(existing);
};

TimeLimitedCache.prototype.get = function(key) {
    return this.cache.has(key) ? this.cache.get(key).value : -1;
};

TimeLimitedCache.prototype.count = function() {
    return this.cache.size;
};