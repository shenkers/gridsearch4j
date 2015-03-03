# gridsearch4j
utility that uses reflection to exhaustively set all cominations of field values on a target object

For example, if you have an object with fields to search

```
class ToSearch {
  String a;
  int b;
  double c;
}
```

A map is used to specify the grid of parameters to search over:

```
// Construct an instance of the object that will be searched
ToSearch obj = new ToSearch();

// Define the grid points to be searched over
Map<String, List> map = new HashMap<String, List>();
map.put("a", Arrays.asList("h",  "j"));
map.put("b", Arrays.asList(2, 4, 5, 7));
map.put("c", Arrays.asList(1., 1.5, 2., 3., 5.));

GridSearchBuilder gsb = new GridSearchBuilder(map);

GridSearch search = gsb.build(ts);

// Iterate over the parameter combinations in the grid
while (search.hasNext()) {
    search.next();
    System.out.printf("obj: a='%s' b='%d' c='%f'\n", ts.a, ts.b, ts.c);
}
```

Fields that are not specified in the map will not be modified. An error will be thrown if incorrect field name or object types are used.
