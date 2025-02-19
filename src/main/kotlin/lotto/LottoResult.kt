package lotto

class LottoResult(val lottos: List<Lotto>) {
    // 각각의 로또 비교하기
    fun compareWithWinningLotto(winningLotto: Lotto): List<Int> {
        return lottos.map { it.lottoNums.count { num -> num in winningLotto.lottoNums } }
    }
}
