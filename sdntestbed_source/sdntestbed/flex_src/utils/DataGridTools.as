package utils
{
	import flash.text.TextLineMetrics;
	
	import mx.controls.DataGrid;
	import mx.controls.dataGridClasses.DataGridColumn;
	
	public class DataGridTools
	{
		/**
		 * set datagrid column's width by header
		 */
		public static function setColumnsWidth(dataGrid:DataGrid):void
		{
			var gridWidth:int=0;
			for(var i:int=0;i<dataGrid.columnCount;i++)
			{
				var column:DataGridColumn=dataGrid.columns[i];
				var colWidth:int=(column.headerText.length+2)*dataGrid.getStyle("fontSize");
				column.width=colWidth;
				gridWidth+=colWidth;
			}
			dataGrid.width=gridWidth;
		}
	}
} 
}