package domain

class NumberRandomGenerator : RandomGenerator {
    override fun generate(): Set<Int> = (1..45).toList().shuffled().subList(0, 6).toSet()
}
