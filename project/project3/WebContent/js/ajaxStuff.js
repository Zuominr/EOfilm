/*
 * CS 122B Project 4. Autocomplete Example.
 * 
 * This Javascript code uses this library: https://github.com/devbridge/jQuery-Autocomplete
 * 
 * This example implements the basic features of the autocomplete search, features that are 
 *   not implemented are mostly marked as "TODO" in the codebase as a suggestion of how to implement them.
 * 
 * To read this code, start from the line "$('#autocomplete').autocomplete" and follow the callback functions.
 * 
 */


/*
 * This function is called by the library when it needs to lookup a query.
 * 
 * The parameter query is the query string.
 * The doneCallback is a callback function provided by the library, after you get the
 *   suggestion list from AJAX, you need to call this function to let the library know.
 */
var map = new HashMap();
function handleLookup(query, doneCallback) {
	console.log("autocomplete initiated")
	console.log("sending AJAX request to backend Java Servlet")
	
	// TODO: if you want to check past query results first, you can do it here
	if(map.containsKey(query)){
		console.log("used cache successful")
		var jsonData = map.get(query);
		doneCallback( { suggestions: jsonData } );
		console.log(jsonData)
	}
	// sending the HTTP GET request to the Java Servlet endpoint hero-suggestion
	// with the query data
	else{
		jQuery.ajax({
			"method": "GET",
			// generate the request url from the query.
			// escape the query string to avoid errors caused by special characters 
			"url": "Autocompletion?query=" + escape(query),
			"success": function(data) {
				// pass the data, query, and doneCallback function into the success handler
				handleLookupAjaxSuccess(data, query, doneCallback) 
			},
			"error": function(errorData) {
				console.log("lookup ajax error")
				console.log(errorData)
			}
		})
	}
}


/*
 * This function is used to handle the ajax success callback function.
 * It is called by our own code upon the success of the AJAX request
 * 
 * data is the JSON data string you get from your Java Servlet
 * 
 */
function handleLookupAjaxSuccess(data, query, doneCallback) {
	console.log("lookup ajax successful")
	
	// parse the string into JSON
	var jsonData = JSON.parse(data);
	console.log(jsonData)
	
	// TODO: if you want to cache the result into a global variable you can do it here
	if(!map.containsKey(query)){
		map.put(query,jsonData);
	}
	// call the callback function provided by the autocomplete library
	// add "{suggestions: jsonData}" to satisfy the library response format according to
	//   the "Response Format" section in documentation
	doneCallback( { suggestions: jsonData } );
}


/*
 * This function is the select suggestion hanlder function. 
 * When a suggestion is selected, this function is called by the library.
 * 
 * You can redirect to the page you want using the suggestion data.
 */
function handleSelectSuggestion(suggestion) {
	// TODO: jump to the specific result page based on the selected suggestion
	
	console.log("you select " + suggestion["value"])
	var url = suggestion["data"]["category"] + "-title" + "?id=" + suggestion["data"]["heroID"]
	console.log(url)
	//wo yao zai zhe xie !!!!!single movie single movie!!!!!! ---by ren zuomin
	if(suggestion["data"]["category"]=="Movie"){
		window.location.href="getMovie?movie_name=" +  suggestion["value"]
	}
	else{
		window.location.href="getStar?star_name=" +  suggestion["value"]
	}
	
}


/*
 * This statement binds the autocomplete library with the input box element and 
 *   sets necessary parameters of the library.
 * 
 * The library documentation can be find here: 
 *   https://github.com/devbridge/jQuery-Autocomplete
 *   https://www.devbridge.com/sourcery/components/jquery-autocomplete/
 * 
 */
// $('#autocomplete') is to find element by the ID "autocomplete"
$('#autocomplete').autocomplete({
	// documentation of the lookup function can be found under the "Custom lookup function" section
    lookup: function (query, doneCallback) {
    		handleLookup(query, doneCallback)
    },
    onSelect: function(suggestion) {
    		handleSelectSuggestion(suggestion)
    },
    // set the groupby name in the response json data field
    groupBy: "category",
    // set delay time
    deferRequestBy: 300,
    // there are some other parameters that you might want to use to satisfy all the requirements
    // TODO: add other parameters, such as mininum characters
   
});


/*
 * do normal full text search if no suggestion is selected 
 */
function handleNormalSearch(query) {
	console.log("doing normal search with query: " + query);
	// TODO: you should do normal search here
}

// bind pressing enter key to a hanlder function
$('#autocomplete').keypress(function(event) {
	// keyCode 13 is the enter key
	if (event.keyCode == 13) {
		// pass the value of the input box to the hanlder function
		handleNormalSearch($('#autocomplete').val())
	}
})

// TODO: if you have a "search" button, you may want to bind the onClick event as well of that button

function HashMap()  
{  
    /** Map 大小 **/  
    var size = 0;  
    /** 对象 **/  
    var entry = new Object();  
      
    /** 存 **/  
    this.put = function (key , value)  
    {  
        if(!this.containsKey(key))  
        {  
            size ++ ;  
        }  
        entry[key] = value;  
    }  
      
    /** 取 **/  
    this.get = function (key)  
    {  
        return this.containsKey(key) ? entry[key] : null;  
    }  
      
    /** 删除 **/  
    this.remove = function ( key )  
    {  
        if( this.containsKey(key) && ( delete entry[key] ) )  
        {  
            size --;  
        }  
    }  
      
    /** 是否包含 Key **/  
    this.containsKey = function ( key )  
    {  
        return (key in entry);  
    }  
      
    /** 是否包含 Value **/  
    this.containsValue = function ( value )  
    {  
        for(var prop in entry)  
        {  
            if(entry[prop] == value)  
            {  
                return true;  
            }  
        }  
        return false;  
    }  
      
    /** 所有 Value **/  
    this.values = function ()  
    {  
        var values = new Array();  
        for(var prop in entry)  
        {  
            values.push(entry[prop]);  
        }  
        return values;  
    }  
      
    /** 所有 Key **/  
    this.keys = function ()  
    {  
        var keys = new Array();  
        for(var prop in entry)  
        {  
            keys.push(prop);  
        }  
        return keys;  
    }  
      
    /** Map Size **/  
    this.size = function ()  
    {  
        return size;  
    }  
      
    /* 清空 */  
    this.clear = function ()  
    {  
        size = 0;  
        entry = new Object();  
    }  
}  
