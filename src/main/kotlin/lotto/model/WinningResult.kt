package lotto.model

class WinningResult private constructor(val result: List<WinningRank>) {
    companion object {
        fun create(
            winningLotto: WinningLotto,
            lottoTickets: List<Lotto>,
        ): WinningResult {
            return WinningResult(
                lottoTickets
                    .map {
                        WinningRank.convert(
                            it.checkWinningNumbers(winningLotto.winning),
                            it.checkBonusNumbers(winningLotto.bonusNumber),
                        )
                    },
            )
        }
    }
}
