package domain

import common.MAXIMUM_LOTTO_RANGE
import common.MINIMUM_LOTTO_RANGE

class NumberRandomGenerator : RandomGenerator {
    override fun generate(): Set<Int> = (MINIMUM_LOTTO_RANGE..MAXIMUM_LOTTO_RANGE).toList().shuffled().take(6).toSet()
}
