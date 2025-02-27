package lotto.model

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LottoWalletTest {
    @Test
    fun `로또를 추가하면 로또 지갑에 로또가 포함된다`() {
        // given
        val lottoWallet = LottoWallet()
        val lottoNumbers = listOf(1, 2, 3, 4, 5, 6)

        // when
        lottoWallet.add(lottoNumbers)

        // then
        assertTrue(lottoWallet.get().map { it.getRawNumbers() }.contains(lottoNumbers))
    }

    @Test
    fun `여러 장의 로또를 추가하면 로또 지갑에 모든 로또가 포함된다`() {
        // given
        val lottoWallet = LottoWallet()
        val lotto1 = Lotto.from(listOf(1, 2, 3, 4, 5, 6))
        val lotto2 = Lotto.from(listOf(7, 8, 9, 10, 11, 12))
        val lottos = listOf(lotto1, lotto2)

        // when
        lottoWallet.addAll(lottos)

        // then
        lottoWallet.get().forEach { lotto ->
            assertTrue(lottoWallet.get().contains(lotto))
        }
    }
}
