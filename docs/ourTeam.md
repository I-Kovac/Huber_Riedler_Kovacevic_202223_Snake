---
layout: page
title: Our Team
permalink: /ourteam/
---

<body style="background-color:#808080">


{% for element in site.data.students %}
{{element.firstname}} {{element.lastname}} <br/>
<img src="../{{element.image}}" alt="{{element.firstname}}"/>
{% endfor %}

</body>
