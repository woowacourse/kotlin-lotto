package domain

import domain.lotto.Lotto
import domain.lotto.LottoBundle
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import util.PREFIX

internal class PurchaserTest {
    @Test
    fun `총 로또 개수는 0과 같거나 작으면 에러 발생 (금액이 1000보다 작으면)`() {
        // given
        val spentMoney = Money(0)

        // when

        // then
        assertThrows<IllegalArgumentException> { Purchaser(spentMoney, 2) }
            .shouldHaveMessage("$PREFIX 구매할 수 있는 로또가 없습니다. 구매하려는 로또 개수: ${0}")
    }

    @Test
    fun `수동으로 구매할 로또 개수가 음수면 에러 발생`() {
        // given
        val manualLottoCount: Int = -1

        // then
        assertThrows<IllegalArgumentException> { Purchaser(Money(1000), manualLottoCount) }
            .shouldHaveMessage("$PREFIX 수동으로 구매할 로또의 개수가 음수일 수 없습니다. 수동 로또 개수: ${-1}")
    }

    @Test
    fun `수동으로 구매할 로또 개수가 전체 로또 개수보다 많으면 에러 발생`() {
        // given
        val spentMoney = Money(5000)
        val manualLottoCount: Int = 6

        // when

        // then
        assertThrows<IllegalArgumentException> { Purchaser(spentMoney, manualLottoCount) }
            .shouldHaveMessage("$PREFIX 수동으로 구매할 로또의 개수가 총 로또 개수보다 많을 수 없습니다. 수동 로또 개수: ${6}, 총 로또 개수: ${5}")
    }

    private val otherLottoBundle = LottoBundle(
        listOf(
            Lotto(1, 2, 3, 4, 5, 6),
            Lotto(2, 3, 4, 5, 6, 7),
        ),
    )

    @Test
    fun `수동 로또가 개수보다 많이 발행되면 에러 발생`() {
        // given
        val purchaser = Purchaser(Money(5000), 1)

        // when

        // then
        assertThrows<IllegalArgumentException> { purchaser.addLottoBundle(otherLottoBundle) }
            .shouldHaveMessage("$PREFIX 수동으로 구매한 로또 개수가 맞지 않습니다. 수동으로 구매할 로또 개수: ${1}, 발행된 수동 로또 개수: ${2}")
    }

    @Test
    fun `수동 로또가 중복 발행되면 에러`() {
        // given
        val purchaser = Purchaser(Money(5000), 2)
        purchaser.addLottoBundle(otherLottoBundle)

        // when

        // then
        assertThrows<IllegalArgumentException> { purchaser.addLottoBundle(otherLottoBundle) }
            .shouldHaveMessage("$PREFIX 이미 수동 로또가 발행되었습니다.")
    }
}
