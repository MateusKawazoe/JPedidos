hljs.initHighlightingOnLoad();



var HC_SETTINGS = {
  css: {
    activeClass: 'is-active',
    hiddenClass: 'is-hidden',
    topbarHiddenClass: 'topbar--hidden',
    topbarFixedClass: 'topbar--fixed'
  }
};

$(function () {
  SearchResultsFilters.init();

  var $window = $(window);
  var $topbar = $('[data-topbar]');
  var topbarHeight = parseInt($topbar.height());

  var bindEffects = function () {
    var scrolled = $window.scrollTop();
    if (scrolled > topbarHeight && scrolled < topbarHeight * 2) {
      $topbar.addClass(HC_SETTINGS.css.topbarHiddenClass);
    } else {
      $topbar
        .removeClass(HC_SETTINGS.css.topbarHiddenClass)
        .removeClass(HC_SETTINGS.css.topbarFixedClass);
    }

    if (scrolled > topbarHeight * 2) {
      $topbar
        .removeClass(HC_SETTINGS.css.topbarHiddenClass)
        .addClass(HC_SETTINGS.css.topbarFixedClass);
    }
  };

  $window.on('scroll.theme', bindEffects);

  if ($('[data-hero-unit="large"]').length === 0) {
    $('[data-menu]')
      .children('[data-toggle-search]')
      .removeClass('hidden');
  }

  var $searchBarMobile = $('[data-search-mobile]');
  var $closeButton = $('<button />', {
    'class': 'btn btn--default btn--search-topbar-close',
    'data-toggle-search': 'true',
    html: $('<span />', { 'class': 'fas fa-times' })
  });

  $searchBarMobile.find('.search-box').append($closeButton);

  $(document).on('click', '[data-toggle-menu]', function () {
    $(this).toggleClass('tcon-transform');
    $('[data-menu]').toggle();
    $topbar.toggleClass('topbar--opened');
  });

  $(document).on('click', '[data-toggle-search]', function () {
    $searchBarMobile.toggleClass('search-box--mobile-active');
  });

  // Social share popups
  $('.share a').click(function (e) {
    e.preventDefault();
    window.open(this.href, '', 'height = 500, width = 500');
  });

  // Toggle the share dropdown in communities
  $('.share-label').on('click', function (e) {
    e.stopPropagation();
    var isSelected = this.getAttribute('aria-selected') === 'true';
    this.setAttribute('aria-selected', !isSelected);
    $('.share-label')
      .not(this)
      .attr('aria-selected', 'false');
  });

  $(document).on('click', function () {
    $('.share-label').attr('aria-selected', 'false');
  });

  // Submit search on select change
  $('#request-status-select, #request-organization-select').on(
    'change',
    function () {
      search();
    }
  );

  // Submit search on input enter
  $('#quick-search').on('keypress', function (e) {
    if (e.which === 13) {
      search();
    }
  });

  function search() {
    window.location.search = $.param({
      query: $('#quick-search').val(),
      status: $('#request-status-select').val(),
      organization_id: $('#request-organization-select').val()
    });
  }

  $('.image-with-lightbox').magnificPopup({
    type: 'image',
    closeOnContentClick: true,
    closeBtnInside: false,
    fixedContentPos: true,
    mainClass: 'mfp-with-zoom', // class to remove default margin from left and right side
    image: {
      verticalFit: true
    },
    zoom: {
      enabled: true,
      duration: 300 // don't foget to change the duration also in CSS
    }
  });

  $('.image-with-video-icon').magnificPopup({
    disableOn: 700,
    type: 'iframe',
    mainClass: 'mfp-fade',
    removalDelay: 160,
    preloader: false,
    fixedContentPos: false
  });

  $('.accordion__item-title').on('click', function () {
    var $title = $(this);
    $title.toggleClass('accordion__item-title--active');
    $title
      .parents('.accordion__item')
      .find('.accordion__item-content')
      .slideToggle();
  });

  $('.tabs-link').click(function (e) {
    e.preventDefault();
    var $link = $(this);
    var tabIndex = $link.index();
    var $tab = $link
      .parents('.tabs')
      .find('.tab')
      .eq(tabIndex);
    $link
      .addClass(HC_SETTINGS.css.activeClass)
      .siblings()
      .removeClass(HC_SETTINGS.css.activeClass);
    $tab
      .removeClass(HC_SETTINGS.css.hiddenClass)
      .siblings('.tab')
      .addClass(HC_SETTINGS.css.hiddenClass);
  });

  // Fix animated icons
  $('.fa-spin').empty();
  $('[data-asynchtml-resource]').removeAttr('data-asynchtml-resource');

  $('img.custom-block__image').each(function () {
    var $img = $(this);
    var imgID = $img.attr('id');
    var imgClass = $img.attr('class');
    var imgURL = $img.attr('src') + '?reset';

    $.get(
      imgURL,
      function (data) {
        // Get the SVG tag, ignore the rest
        var $svg = $(data).find('svg');

        // Add replaced image's ID to the new SVG
        if (typeof imgID !== 'undefined') {
          $svg = $svg.attr('id', imgID);
        }
        // Add replaced image's classes to the new SVG
        if (typeof imgClass !== 'undefined') {
          $svg = $svg.attr('class', imgClass + ' replaced-svg');
        }

        // Remove any invalid XML tags as per http://validator.w3.org
        $svg = $svg.removeAttr('xmlns:a');

        // Replace image with new SVG
        $img.replaceWith($svg);
      },
      'xml'
    );
  });

  $("#categories-titles").on('change', e => {
    e.preventDefault();

    for (let i = 0; i < $(".block-list-item__body--accordion-content").length; i++) {
      if ($(".block-list-item__body--accordion-content")[i].classList.contains("selected-accordion"))
        $(".block-list-item__body--accordion-content")[i].classList.remove("selected-accordion")
    }

    $(".block-list-item__body--accordion-content")[e.target.selectedIndex]
      .classList.add("selected-accordion");
  })

  $('[data-block-accordion-title]').on('click', function (e) {
    e.preventDefault();
    $('.DCSR-146-active-category-button').removeClass('DCSR-146-active-category-button')
    e.target.parentNode.classList.add('DCSR-146-active-category-button')

    for (let i = 0; i < $(".block-list-item__body--accordion-content").length; i++) {
      if ($(".block-list-item__body--accordion-content")[i].classList.contains("selected-accordion"))
        $(".block-list-item__body--accordion-content")[i].classList.remove("selected-accordion")
    }

    $(".block-list-item__body--accordion-content")[$(".DCSR-146-category-button").index(e.target.parentNode.parentNode)]
      .classList.add("selected-accordion");

    // var $title = $(this);
    // $title.toggleClass(HC_SETTINGS.css.activeClass);
    // $title
    //   .parents('[data-block-accordion-item]')
    //   .find('[data-block-accordion-content]')
    //   .slideToggle();
  });

  /* Customização de elementos */
  var CurrLocale = document.querySelector("html").getAttribute('lang').toLowerCase();
  var siteLocale = document.querySelector("html").getAttribute('lang').toLowerCase();

  $(".logo a", ".back-site").attr("href", "https://www.gympass.com/" + siteLocale);
  $(".language-selector span a[href*='/" + CurrLocale + "?']").hide();
  $(".search #query").attr("placeholder", searchTitle);
  $(".language-selector .dropdown-toggle").html(CurrentLanguage);
});

$(window).on("load", () => {
  $(".DCSR-146-category-button")[0].children[0].classList.add("DCSR-146-active-category-button");
  $(".block-list-item__body--accordion-content")[0].classList.add("selected-accordion");

  for (let i = 0; i < $(".promoted-section-tree-items").length; i++) {
    if ($(".promoted-section-tree-items")[i].childNodes.length === 3) {
      $(".promoted-section-tree-items")[i].style.left = "-900px";
      $(".promoted-section-tree-items")[i].style.position = "absolute";
      $(".promoted-section-tree-items")[i].parentNode.parentNode.childNodes[3].style.right = "0"
      $(".promoted-section-tree-items")[i].parentNode.parentNode.childNodes[3].style.position = "relative"
      $(".promoted-section-tree-items")[i].parentNode.parentNode.childNodes[3].childNodes[3].style.display = "none";
    }
  }
})

$(".section-tree-item > div").on('click', e => {
  if (e.currentTarget.parentNode.childNodes[3].classList.contains("clicked-arrow")) {
    if (e.currentTarget.parentNode.childNodes.length === 11) {
      setTimeout(() => {
        e.currentTarget.parentNode.childNodes[9].style.display = "none"
      }, 250)
    }

    e.currentTarget.parentNode.childNodes[3].classList.remove("clicked-arrow");
    e.currentTarget.parentNode.childNodes[7].classList.remove("selected-article-list");
    e.currentTarget.parentNode.style.maxHeight = "44px";
  } else {
    if (e.currentTarget.parentNode.childNodes.length === 11) {
      setTimeout(() => {
        e.currentTarget.parentNode.childNodes[9].style.display = "unset"
      }, 250)
    }

    e.currentTarget.parentNode.childNodes[3].classList.add("clicked-arrow");
    e.currentTarget.parentNode.childNodes[7].classList.add("selected-article-list");
    e.currentTarget.parentNode.style.maxHeight = "500px";
  }
})

$(".promoted-article-button").on('click', e => {
  e.preventDefault();
  e.currentTarget.parentNode.style.left = "-900px";
  e.currentTarget.parentNode.parentNode.parentNode.childNodes[3].style.right = 0;
  setTimeout(() => {
    e.currentTarget.parentNode.parentNode.style.position = "absolute";
    e.currentTarget.parentNode.parentNode.parentNode.childNodes[3].style.position = "relative";
  }, 300)
})

$(".article-button").on('click', e => {
  e.preventDefault();
  e.currentTarget.parentNode.style.right = "-900px";
  e.currentTarget.parentNode.parentNode.childNodes[1].childNodes[1].style.left = 0;
  setTimeout(() => {
    e.currentTarget.parentNode.style.position = "absolute";
    e.currentTarget.parentNode.parentNode.childNodes[1].style.position = "relative";
  }, 300)
})