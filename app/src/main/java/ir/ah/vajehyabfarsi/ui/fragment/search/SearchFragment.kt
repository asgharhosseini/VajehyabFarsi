package ir.ah.vajehyabfarsi.ui.fragment.search

import android.widget.*
import ir.ah.vajehyabfarsi.*
import ir.ah.vajehyabfarsi.base.*
import ir.ah.vajehyabfarsi.databinding.*
import ir.ah.vajehyabfarsi.other.*

class SearchFragment :
    BaseFragment<SearchViewModel>(R.layout.fragment_search, SearchViewModel::class) {
    private val binding by viewBinding(FragmentSearchBinding::bind)


    val items = listOf(
        "همه", "لغتنامهٔ دهخدا", "فرهنگ فارسی معین", "فرهنگ فارسی عمید", "واژگان مترادف و متضاد", "فرهنگ واژه های مصوّب فرهنگستان",
        "واژه های فارسی سره", "فرهنگ گنج واژه", "واژه نامهٔ آزاد", "اصطلاحات عامیانه",
        "فرهنگ واژگان قرآن", "فرهنگ نام ها", "فرهنگ لغات علمی", "لهجه و گویش اصفهانی",
        "لهجه و گویش بختیاری", "لهجه و گویش تهرانی", "لهجه و گویش دزفولی", "لهجه و گویش گنابادی",
        "لهجه و گویش مازنی", "دیکشنری انگلیسی به فارسی", "دیکشنری عربی به فارسی", "دیکشنری فارسی به انگلیسی", "دیکشنری فارسی به عربی",
    )
    val adapter = ArrayAdapter(requireContext(), R.layout.list_filer, items)

    override fun observeData() {
        super.observeData()
    }

    override fun setUpViews() {
        super.setUpViews()
        binding.searchFilter.setAdapter(adapter)
    }
}