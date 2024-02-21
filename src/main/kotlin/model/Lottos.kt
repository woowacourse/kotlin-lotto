package lotto.model

import model.Lotto

class Lottos(val lottos: MutableList<Lotto> = mutableListOf()) {
    fun add(lotto: Lotto) {
        lottos.add(lotto)
    }
}
