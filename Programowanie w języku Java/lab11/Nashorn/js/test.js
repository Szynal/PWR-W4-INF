function process(input) {
	var output = input.split('');
	var arrayLength = output.length;
	for (var i = 0; i < arrayLength; i++) {
		output[i] = '*';
	}
	return output.join("");
}