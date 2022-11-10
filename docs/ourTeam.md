{% for element in site.data.students %}
{{element.firstname}} {{element.lastname}} <br/>
<img src="{{element.image}}" alt="element.firstname"/>
{% endfor %}

change2
