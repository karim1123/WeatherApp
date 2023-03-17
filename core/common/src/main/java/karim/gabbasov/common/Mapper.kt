package karim.gabbasov.common

interface Mapper<in FromObject, out ToObject> {
    fun map(fromObject: FromObject): ToObject
}
