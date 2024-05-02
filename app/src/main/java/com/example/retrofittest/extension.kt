package com.example.retrofittest

inline fun <reified T: Any> newInstance(): T {
    // INSTANCE Field에 접근
    val fragClass = T::class.java.getDeclaredField("INSTANCE")

    return synchronized(fragClass) {
        val instance = fragClass.get(null) as? T

        // 이미 생성된 객체가 있는 경우
        if (instance != null) {
            return instance
        }

        // 생성된 객체가 없는 경우 -> 객체 생성 및 생성된 객체를 필드에 저장
        val newInstance = T::class.java.newInstance()

        return newInstance.also {
            fragClass.set(null, it)
        }
    }
}