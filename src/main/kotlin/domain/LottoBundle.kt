package domain

class LottoBundle(manualLottos: List<Lotto>, autoLottos: List<Lotto>) {
    val lottos: List<Lotto> = manualLottos + autoLottos
}
