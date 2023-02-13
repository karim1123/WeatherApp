package karim.gabbasov.common

interface Mapper<in FromObject, ToObject> {
    fun map(fromObject: FromObject): ToObject
}
