package lotto.entity

class PurchasedLottos(val value: List<Lotto>) {
    operator fun plus(other: PurchasedLottos): PurchasedLottos = PurchasedLottos(value + other.value)
}
